package com.example.mysampleproject.interfaces.impl

import com.example.mysampleproject.entity.ArticleEntity
import com.example.mysampleproject.interfaces.ArticleListItemInterface

class ArticleListItemInterfaceImpl(
    override val onArticleItemClick: (ArticleEntity) -> Unit,
    override val onArticleCollectClick: (ArticleEntity) -> Unit
) : ArticleListItemInterface {

}