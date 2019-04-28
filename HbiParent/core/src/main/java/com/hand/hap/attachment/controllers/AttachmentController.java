//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.attachment.controllers;

import com.hand.hap.attachment.FileInfo;
import com.hand.hap.attachment.Uploader;
import com.hand.hap.attachment.UploaderFactory;
import com.hand.hap.attachment.dto.AttachCategory;
import com.hand.hap.attachment.dto.Attachment;
import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.attachment.exception.AttachmentException;
import com.hand.hap.attachment.exception.FileReadIOException;
import com.hand.hap.attachment.exception.UniqueFileMutiException;
import com.hand.hap.attachment.service.IAttachCategoryService;
import com.hand.hap.attachment.service.ISysFileService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.TokenException;
import com.hand.hap.core.util.FormatUtil;
import com.hand.hap.core.util.UploadUtil;
import com.hand.hap.security.TokenUtils;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AttachmentController extends BaseController {
    private static final String MESSAGE_NAME = "message";
    private static final String MESG_UNIQUE = "Unique";
    private static final String MESG_SUCCESS = "success";
    private static final String INFO_NAME = "info";
    private static final String TYPEORKEY_EMPTY = "TYPEORKEY_EMPTY";
    private static final String TYPE_ERROR = "SOURCETYPE_ERROR";
    private static final String DATABASE_ERROR = "DATABASE_ERROR";
    private static final String IMAGE_MIME_PREFIX = "image";
    private static final String FILE_NAME = "file";
    private static final Integer BUFFER_SIZE = 1024;
    private static final Float COMPRESS_SIZE = 40.0F;
    private static final Float BYTE_TO_KB = 1024.0F;
    private static final String ENC = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);
    @Autowired
    private IAttachCategoryService categoryService;
    @Autowired
    private ISysFileService fileService;
    @Autowired
    private MessageSource messageSource;

    public AttachmentController() {
    }

    @RequestMapping(
            value = {"/sys/attach/manage"},
            method = {RequestMethod.GET}
    )
    public ModelAndView manager(HttpServletRequest request, String sourceType, String sourceKey) {
        request.setAttribute("sourceType", sourceType);
        request.setAttribute("sourceKey", sourceKey);
        IRequest requestContext = this.createRequestContext(request);
        AttachCategory category = this.categoryService.selectAttachByCode(requestContext, sourceType);
        if (category != null) {
            request.setAttribute("files", this.fileService.selectFilesByTypeAndKey(requestContext, sourceType, sourceKey));
            request.setAttribute("message", "success");
        }

        return new ModelAndView(this.getViewPath() + "/attach/sys_attach_manage");
    }

    @RequestMapping(
            value = {"/sys/attach/upload"},
            method = {RequestMethod.POST},
            produces = {"text/plain;charset=UTF-8"}
    )
    public ResponseData upload(HttpServletRequest request, Locale locale, String contextPath) throws Exception {
        Uploader uploader = UploaderFactory.getMutiUploader();
        uploader.init(request);
        String status = uploader.getStatus();
        if ("NOT_FILE_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.not.file.form", "error.upload.not.file.form", new Object[0]);
        } else if ("LIMIT_SIZE_MAX_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.total.size.limit.exceeded", "error.upload.total.size.limit.exceeded", new Object[0]);
        } else if ("LIMIT_UPLOAD_NUM_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.total.num.limit.exceeded", "error.upload.total.num.limit.exceeded", new Object[0]);
        } else if ("UPLOAD_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.unknown", "error.upload.unknown", new Object[0]);
        } else {
            String sourceType = uploader.getParams("sourceType");
            String sourceKey = uploader.getParams("sourceKey");
            String associationTableId = uploader.getParams("associationTableId");
            ResponseData response = new ResponseData();
            response.setMessage(this.messageSource.getMessage("hap.upload_success", (Object[])null, locale));
            if (StringUtils.isBlank(sourceType)) {
                throw new AttachmentException("msg.warning.upload.sourcetype.empty", "msg.warning.upload.sourcetype.empty", new Object[0]);
            } else {
                IRequest requestContext = this.createRequestContext(request);
                AttachCategory category = this.categoryService.selectAttachByCode(requestContext, sourceType);
                if (category == null) {
                    throw new AttachmentException("msg.warning.upload.folder.notfound", "msg.warning.upload.folder.notfound", new String[]{sourceType});
                } else {
                    UploadUtil.initUploaderParams(uploader, category);
                    List<FileInfo> fileInfoList = uploader.upload();
                    status = uploader.getStatus();
                    if ("FILE_PROCESS_ERROR".equals(status)) {
                        throw new AttachmentException("error.upload.file.process", "error.upload.file.process", new Object[0]);
                    } else {
                        Attachment attach = UploadUtil.genAttachment(category, sourceKey, requestContext.getUserId(), requestContext.getUserId());
                        List<SysFile> sysFileList = new ArrayList();
                        Iterator var14 = fileInfoList.iterator();

                        while(var14.hasNext()) {
                            FileInfo fileInfo = (FileInfo)var14.next();

                            try {
                                SysFile sysFile = UploadUtil.genSysFile(fileInfo, requestContext.getUserId(), requestContext.getUserId());

                                if ("Y".equals(category.getIsUnique())) {
                                    sysFile = this.fileService.updateOrInsertFile(requestContext, attach, sysFile);
                                } else {
                                    this.fileService.insertFileAndAttach(requestContext, attach, sysFile);
                                }

                                sysFile.setFilePath((String)null);
                                sysFile.setFileSizeDesc(FormatUtil.formatFileSize(sysFile.getFileSize()));
                                TokenUtils.generateAndSetToken(TokenUtils.getSecurityKey(request.getSession(false)), sysFile);
                                sysFileList.add(sysFile);
                            } catch (UniqueFileMutiException var18) {
                                response.setSuccess(false);
                                response.setMessage(this.messageSource.getMessage("hap.mesg_unique", (Object[])null, locale));
                                break;
                            } catch (Exception var19) {
                                if (logger.isErrorEnabled()) {
                                    logger.error("database error", var19);
                                }

                                File file = fileInfo.getFile();
                                if (file.exists()) {
                                    file.delete();
                                }

                                response.setSuccess(false);
                                break;
                            }
                        }

                        response.setRows(sysFileList);
                        return response;
                    }
                }
            }
        }
    }

    @RequestMapping({"/sys/attach/remove"})
    public Map<String, Object> remove(HttpServletRequest request, String fileId, String token) throws TokenException {
        Map<String, Object> response = new HashMap(16);
        IRequest requestContext = this.createRequestContext(request);
        SysFile file = new SysFile();
        file.setFileId(Long.valueOf(fileId));
        file.set_token(token);
        TokenUtils.checkToken(request.getSession(false), file);
        this.fileService.delete(requestContext, file);
        response.put("message", "success");
        return response;
    }

    @RequestMapping({"/sys/attach/file/detail", "/sys/attach/file/download"})
    public void detail(HttpServletRequest request, HttpServletResponse response, String fileId, String token) throws Exception {
        IRequest requestContext = this.createRequestContext(request);
        SysFile sysFile = this.fileService.selectByPrimaryKey(requestContext, Long.valueOf(fileId));
        sysFile.set_token(token);
        TokenUtils.checkToken(request.getSession(false), sysFile);
        File file = new File(sysFile.getFilePath());
        if (file.exists()) {
            response.addHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(sysFile.getFileName(), "UTF-8") + "\"");
            response.setContentType(sysFile.getFileType() + ";charset=" + "UTF-8");
            response.setHeader("Accept-Ranges", "bytes");
            int fileLength = (int)file.length();
            response.setContentLength(fileLength);
            if (fileLength > 0) {
                this.writeFileToResp(response, file);
            }

        } else {
            throw new AttachmentException("msg.warning.download.file.error", "msg.warning.download.file.error", new Object[0]);
        }
    }

    @RequestMapping({"/sys/attach/file/view"})
    public void view(HttpServletRequest request, HttpServletResponse response, String fileId, String compress) throws Exception {
        IRequest requestContext = this.createRequestContext(request);
        SysFile sysFile = this.fileService.selectByPrimaryKey(requestContext, Long.valueOf(fileId));

        try {
            File file = new File(sysFile.getFilePath());
            if (!file.exists()) {
                throw new AttachmentException("msg.warning.download.file.error", "msg.warning.download.file.error", new Object[0]);
            } else {
                response.setHeader("cache-control", "must-revalidate");
                response.setHeader("pragma", "public");
                response.setHeader("Content-Type", sysFile.getFileType());
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("Content-disposition", "attachment;" + this.processFileName(request, sysFile.getFileName()));
                int fileLength = (int)file.length();
                response.setContentLength(fileLength);
                if (fileLength > 0) {
                    if (StringUtils.isNotBlank(compress) && "Y".equals(compress)) {
                        OutputStream os = response.getOutputStream();
                        Throwable var10 = null;

                        try {
                            Thumbnails.of(new File[]{file}).scale((double)this.getCompressPercent((long)fileLength)).toOutputStream(os);
                            os.flush();
                        } catch (Throwable var20) {
                            var10 = var20;
                            throw var20;
                        } finally {
                            if (os != null) {
                                if (var10 != null) {
                                    try {
                                        os.close();
                                    } catch (Throwable var19) {
                                        var10.addSuppressed(var19);
                                    }
                                } else {
                                    os.close();
                                }
                            }

                        }
                    } else {
                        this.writeFileToResp(response, file);
                    }
                }

            }
        } catch (IOException var22) {
            logger.error(var22.getMessage(), var22);
            throw new FileReadIOException();
        }
    }

    private String processFileName(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
        String userAgent = request.getHeader("User-Agent");
        String new_filename = URLEncoder.encode(filename, "UTF8");
        String rtn = "filename=\"" + new_filename + "\"";
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.indexOf("msie") != -1) {
                rtn = "filename=\"" + new String(filename.getBytes("GB2312"), "ISO-8859-1") + "\"";
            } else if (userAgent.indexOf("safari") == -1 && userAgent.indexOf("applewebkit") == -1) {
                if (userAgent.indexOf("opera") != -1 || userAgent.indexOf("mozilla") != -1) {
                    rtn = "filename*=UTF-8''" + new_filename;
                }
            } else {
                rtn = "filename=\"" + new String(filename.getBytes("UTF-8"), "ISO8859-1") + "\"";
            }
        }

        return rtn;
    }

    private void writeFileToResp(HttpServletResponse response, File file) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        InputStream inStream = new FileInputStream(file);
        Throwable var5 = null;

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            Throwable var7 = null;

            try {
                int readLength;
                while((readLength = inStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, readLength);
                }

                outputStream.flush();
            } catch (Throwable var30) {
                var7 = var30;
                throw var30;
            } finally {
                if (outputStream != null) {
                    if (var7 != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable var29) {
                            var7.addSuppressed(var29);
                        }
                    } else {
                        outputStream.close();
                    }
                }

            }
        } catch (Throwable var32) {
            var5 = var32;
            throw var32;
        } finally {
            if (inStream != null) {
                if (var5 != null) {
                    try {
                        inStream.close();
                    } catch (Throwable var28) {
                        var5.addSuppressed(var28);
                    }
                } else {
                    inStream.close();
                }
            }

        }

    }

    private float getCompressPercent(long len) {
        return (float)len / (BYTE_TO_KB * COMPRESS_SIZE);
    }
}
