package com.example.mysampleproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.lib_recyclerview.adapter.base.BaseRvListDBAdapter
import com.example.lib_recyclerview.adapter.simple.SimpleRvAdapter
import com.example.lib_recyclerview.holder.BaseRvDBViewHolder
import com.example.lib_recyclerview.holder.BaseRvViewHolder
import com.example.lib_recyclerview.layoutmanager.WrapContentLinearLayoutManager
import com.example.mysampleproject.R
import com.example.mysampleproject.databinding.AppRecyclerItemArticlesBinding
import com.example.mysampleproject.entity.ArticleEntity
import com.example.mysampleproject.entity.ArticleTagEntity
import com.example.mysampleproject.interfaces.ArticleListItemInterface

/**
 * 首页文章列表列表适配器类
 */
class ArticleListRvAdapter
    : BaseRvListDBAdapter<
        ArticleListRvAdapter.ViewHolder,
        AppRecyclerItemArticlesBinding,
        ArticleListItemInterface,
        ArticleEntity>() {

    override val layoutResID: Int = R.layout.app_recycler_item_articles

    override fun convert(holder: BaseRvViewHolder<ArticleEntity>, entity: ArticleEntity) {
        super.convert(holder, entity)
        (holder as? ViewHolder)?.mBinding?.let { mBinding ->
            val adapter = SimpleRvAdapter<ArticleTagEntity>(R.layout.app_recycler_item_article_tags)
            adapter.viewModel = viewModel
            adapter.mData.addAll(entity.tags.orEmpty())
            mBinding.rvArticlesTags.layoutManager = WrapContentLinearLayoutManager(RecyclerView.HORIZONTAL)
            mBinding.rvArticlesTags.adapter = adapter
        }
    }

    class ViewHolder(binding: AppRecyclerItemArticlesBinding)
        : BaseRvDBViewHolder<AppRecyclerItemArticlesBinding, ArticleEntity>(binding)
}