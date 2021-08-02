package com.example.mysampleproject

class JSON(
    private val unquoted: Boolean = false,
    private val indented: Boolean = false,
    private val indent: String = "    ",
    internal val strictMode: Boolean = true,
    val updateMode: UpdateMode = UpdateMode.OVERWRITE,
    val encodeDefaults: Boolean = true
)