package com.olq.piggifyme.data.database

/**
 * Created by olq on 17.11.17.
 */
class Triplet(map: MutableMap<String, Any?>) {
    var type: String by map
    var source: String by map
    var value: Int by map

    constructor(type: String, source: String, value: Int) : this(HashMap()) {
        this.type = type
        this.source = source
        this.value = value
    }
}