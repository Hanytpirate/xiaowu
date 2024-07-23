package com.xiaowu.doc.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.xiaowu.doc.service.IXiaowuDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaowu.doc.mapper.XiaowuDocMapper;
import com.xiaowu.doc.domain.XiaowuDoc;

/**
 * 文档管理Service业务层处理
 *
 * @author hanyt
 * @date 2024-07-22
 */
@Service
public class XiaowuDocServiceImpl implements IXiaowuDocService
{
    @Autowired
    private XiaowuDocMapper xiaowuDocMapper;

    /**
     * 查询文档管理
     *
     * @param docId 文档管理主键
     * @return 文档管理
     */
    @Override
    public XiaowuDoc selectXiaowuDocByDocId(Long docId)
    {
        return xiaowuDocMapper.selectXiaowuDocByDocId(docId);
    }

    /**
     * 查询文档管理列表
     *
     * @param xiaowuDoc 文档管理
     * @return 文档管理
     */
    @Override
    public List<XiaowuDoc> selectXiaowuDocList(XiaowuDoc xiaowuDoc)
    {
        return xiaowuDocMapper.selectXiaowuDocList(xiaowuDoc);
    }

    /**
     * 新增文档管理
     *
     * @param xiaowuDoc 文档管理
     * @return 结果
     */
    @Override
    public int insertXiaowuDoc(XiaowuDoc xiaowuDoc)
    {
        xiaowuDoc.setCreateTime(DateUtils.getNowDate());
        return xiaowuDocMapper.insertXiaowuDoc(xiaowuDoc);
    }

    /**
     * 修改文档管理
     *
     * @param xiaowuDoc 文档管理
     * @return 结果
     */
    @Override
    public int updateXiaowuDoc(XiaowuDoc xiaowuDoc)
    {
        return xiaowuDocMapper.updateXiaowuDoc(xiaowuDoc);
    }

    /**
     * 批量删除文档管理
     *
     * @param docIds 需要删除的文档管理主键
     * @return 结果
     */
    @Override
    public int deleteXiaowuDocByDocIds(Long[] docIds)
    {
        return xiaowuDocMapper.deleteXiaowuDocByDocIds(docIds);
    }

    /**
     * 删除文档管理信息
     *
     * @param docId 文档管理主键
     * @return 结果
     */
    @Override
    public int deleteXiaowuDocByDocId(Long docId)
    {
        return xiaowuDocMapper.deleteXiaowuDocByDocId(docId);
    }
}
