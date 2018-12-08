package com.cat.eosinfo.repo.model

data class Action(
    val account: String,
    val authorization: List<Authorization>,
    val `data`: Data,
    val hex_data: String,
    val name: String
)