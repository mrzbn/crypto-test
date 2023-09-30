package com.freedom.cryptocurrency.connection

sealed class ConnectionStateModel {
    object Available : ConnectionStateModel()
    object Unavailable : ConnectionStateModel()
}