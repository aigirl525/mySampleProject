package com.example.mysampleproject.interfaces

import com.example.mysampleproject.entity.ArticleEntity


/**
 * 文章列表点击事件接口
 *
 * - 创建时间：2021/1/22
 *
 * @author 王杰
 */
interface ArticleListItemInterface {

    /** 文章列表条目点击 */
    val onArticleItemClick: (ArticleEntity) -> Unit

    /** 文章收藏点击 */
    val onArticleCollectClick: (ArticleEntity) -> Unit
}