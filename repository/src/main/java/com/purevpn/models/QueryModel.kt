package com.purevpn.models

data class QueryModel<DATA_CLASS, DATA>(var dataClass: Class<DATA_CLASS>, var dataList: List<QueryDataModel<DATA>>)