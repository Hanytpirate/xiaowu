package com.xiaowu.doc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 文档管理对象 xiaowu_doc
 *
 * @author hanyt
 * @date 2024-07-22
 */
public class XiaowuDoc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文档id */
    private Long docId;

    /** 团队id */
    @Excel(name = "团队id")
    private Long teamId;

    /** 文档类型 */
    @Excel(name = "文档类型")
    private String docType;

    /** 文档名称 */
    @Excel(name = "文档名称")
    private String docName;

    /** 文档介绍 */
    @Excel(name = "文档介绍")
    private String introduction;

    public void setDocId(Long docId)
    {
        this.docId = docId;
    }

    public Long getDocId()
    {
        return docId;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }
    public void setDocType(String docType)
    {
        this.docType = docType;
    }

    public String getDocType()
    {
        return docType;
    }
    public void setDocName(String docName)
    {
        this.docName = docName;
    }

    public String getDocName()
    {
        return docName;
    }
    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("docId", getDocId())
            .append("teamId", getTeamId())
            .append("docType", getDocType())
            .append("docName", getDocName())
            .append("introduction", getIntroduction())
            .append("createTime", getCreateTime())
            .toString();
    }
}
