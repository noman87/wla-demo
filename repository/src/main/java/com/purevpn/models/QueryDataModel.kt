package com.purevpn.models

import com.purevpn.core.enums.DatabaseOperations

data class QueryDataModel<DATA>(var columnName: String, var dbOperation: DatabaseOperations? = null, var data: DATA?) {
}