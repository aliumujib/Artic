package com.aliumujib.artic.domain.exceptions

import java.lang.IllegalStateException

class EmptyQueryException(errorMessage: String = "Your query cannot be empty") : Exception(errorMessage)