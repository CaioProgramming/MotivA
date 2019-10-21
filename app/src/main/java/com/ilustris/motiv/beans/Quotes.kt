package com.ilustris.motiv.beans

class Quotes {

    var id: String? = null
    var quote: String? = null
    var author: String? = null
    var data: String? = null
    var categoria: String? = null
    var userID: String? = null
    var username: String? = null
    var userphoto: String? = null
    var backgroundcolor: Int = 0
    var textcolor: Int = 0


    var isItalic: Boolean = false
    var isBold: Boolean = false


    var isReport: Boolean = false

    var font: Int? = null


    constructor(id: String,
                quote: String,
                author: String,
                data: String,
                categoria: String,
                userID: String,
                username: String,
                userphoto: String,
                backgroundcolor: Int,
                textcolor: Int,
                italic: Boolean,
                bold: Boolean,
                font: Int?,
                report: Boolean) {
        this.id = id
        this.quote = quote
        this.author = author
        this.data = data
        this.categoria = categoria
        this.userID = userID
        this.username = username
        this.userphoto = userphoto
        this.backgroundcolor = backgroundcolor
        this.textcolor = textcolor
        this.isItalic = italic
        this.isBold = bold
        this.font = font
        this.isReport = report
    }

    constructor() {}


}
