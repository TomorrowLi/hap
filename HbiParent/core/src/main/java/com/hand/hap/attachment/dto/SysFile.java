//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.attachment.dto;

import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotEmpty;

@Table(
        name = "sys_file"
)
public class SysFile extends BaseDTO {
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(
            generator = "IDENTITY"
    )
    private Long fileId;
    @Column
    private Long attachmentId;
    @Column
    @NotEmpty
    @OrderBy
    private String fileName;
    @Column
    private String filePath;
    @Column
    @OrderBy
    private Long fileSize;
    @Transient
    private String fileSizeDesc;
    @Column
    @OrderBy
    private String fileType;
    @Column
    @OrderBy
    private Date uploadDate;

    public SysFile() {
    }

    public Long getFileId() {
        return this.fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getAttachmentId() {
        return this.attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileSizeDesc() {
        return this.fileSizeDesc;
    }

    public void setFileSizeDesc(String fileSizeDesc) {
        this.fileSizeDesc = fileSizeDesc;
    }
}
