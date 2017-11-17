package com.olq.piggifyme.data.database

/**
 * Created by olq on 17.11.17.
 */
class Triplet(var map: MutableMap<String, Any?>) {
    var _id: Long by map
    var type: String by map
    var source: String by map
    var value: Int by map

//    constructor(type: String, source: String, value: Int) : this(HashMap()) {
    constructor(source: String, value: Int) : this(HashMap()) {
        this.source = source
        this.value = value
    }
}