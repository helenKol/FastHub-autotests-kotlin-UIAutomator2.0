package com.fasthub.suites

import com.fasthub.tests.AuthTests
import com.fasthub.tests.OverviewTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(AuthTests::class, OverviewTests::class)
class SmokeSuite