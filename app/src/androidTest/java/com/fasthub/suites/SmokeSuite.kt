package com.fasthub.suites

import com.fasthub.tests.AuthTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(AuthTests::class)
class SmokeSuite