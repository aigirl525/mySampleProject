package com.example.mysampleproject.fragment

import androidx.fragment.app.Fragment

class MyFragment : Fragment(){
    companion object{
        /** 创建 [HomepageFragment] 并返回 */
        fun actionCreate():MyFragment{
            return MyFragment()
        }
    }
}