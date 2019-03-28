package com.purevpn.core.enums

class DatabaseOperations {

    var selectOperations: SelectOperations? = null
    var logicalOperations: LogicalOperations? = null


    enum class SelectOperations { EQUAL_TO, NOT_EQUAL_TO }
    enum class LogicalOperations { AND, OR, CONTAINS }

}

