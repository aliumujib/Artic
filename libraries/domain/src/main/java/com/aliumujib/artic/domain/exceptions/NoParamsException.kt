package com.aliumujib.artic.domain.exceptions

import java.lang.IllegalStateException

class NoParamsException(errorMessage: String = "Your params can't be null for this use case") : Exception(errorMessage)