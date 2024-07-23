package com.xiaowu.doc.mapper;

import java.util.List;
import com.xiaowu.doc.domain.XiaowuDoc;

/**
 * 文档管理Mapper接口
 *
 * @author hanyt
 * @date 2024-07-22
 */
public interface XiaowuDocMapper
{
    /**
     * 查询文档管理
     *
     * @param docId 文档管理主键
     * @return 文档管理
     */
    public XiaowuDoc selectXiaowuDocByDocId(Long docId);

    /**
     * 查询文档管理列表
     *
     * @param xiaowuDoc 文档管理
     * @return 文档管理集合
     */
    public List<XiaowuDoc> selectXiaowuDocList(XiaowuDoc xiaowuDoc);

    /**
     * 新增文档管理
     *
     * @param xiaowuDoc 文档管理
     * @return 结果
     */
    public int insertXiaowuDoc(XiaowuDoc xiaowuDoc);

    /**
     * 修改文档管理
     *
     * @param xiaowuDoc 文档管理
     * @return 结果
     */
    public int updateXiaowuDoc(XiaowuDoc xiaowuDoc);

    /**
     * 删除文档管理
     *
     * @param docId 文档管理主键
     * @return 结果
     */
    public int deleteXiaowuDocByDocId(Long docId);

    /**
     * 批量删除文档管理
     *
     * @param docIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXiaowuDocByDocIds(Long[] docIds);
}
