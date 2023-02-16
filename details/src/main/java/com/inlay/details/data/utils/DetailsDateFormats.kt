package com.inlay.details.data.utils

import java.text.SimpleDateFormat
import java.util.*

val longDateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
val longDateFormatter = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ENGLISH)

val shortDateParser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
val shortDateFormatter = SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH)