package com.example.mysampleproject.net

import com.example.mysampleproject.entity.ArticleEntity
import com.example.mysampleproject.entity.ArticleListEntity
import com.example.mysampleproject.entity.BannerEntity
import com.example.mysampleproject.entity.UserInfoEntity
import retrofit2.http.*

interface WebService {
    /** 通过用户名[username]、密码[password]注册用户并返回用户信息 */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String = password
    ): NetResult<UserInfoEntity>

    /** 通过用户名[username]、密码[password]登录并返回用户信息 */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): NetResult<UserInfoEntity>

    /** 获取并返回首页 Banner 列表 */
    @GET("/banner/json")
    suspend fun getHomepageBannerList(): NetResult<ArrayList<BannerEntity>>
    /** 根据页码[pageNum]获取并返回首页文章列表 */
    @GET("/article/list/{pageNum}/json")
    suspend fun getHomepageArticleList(@Path("pageNum") pageNum: Int): NetResult<ArticleListEntity>
    /** 获取并返回首页置顶文章列表 */
    @GET("/article/top/json")
    suspend fun getHomepageTopArticleList(): NetResult<ArrayList<ArticleEntity>>
}