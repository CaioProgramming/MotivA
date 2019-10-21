package com.ilustris.motiv.beans

class Pics {

    var uri: String? = null
    var id: String? = null

    var name: String? = null

    constructor() {}

    constructor(uri: String, id: String, name: String) {
        this.uri = uri
        this.id = id
        this.name = name
    }
}
