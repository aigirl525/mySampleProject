package com.example.mysampleproject.repository

import com.example.mysampleproject.constants.REQUEST_TIMEOUT
import com.example.mysampleproject.ext.netRequest
import com.example.mysampleproject.net.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class UserRepository(private val webService: WebService) {
    /** 通过用户名[username]、密码[password]登录并返回用户信息 */
    suspend fun login(username: String, password: String) =
        netRequest { webService.login(username, password) }


    /** 通过用户名[username]、密码[password]注册用户并返回用户信息 */
    suspend fun register(username: String, password: String) = withContext(Dispatchers.IO) {
        withTimeout(REQUEST_TIMEOUT) {
            webService.register(username, password)

        }
    }
}