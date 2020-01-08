package com.ahrybuk.swivltest

import com.ahrybuk.swivltest.api.configuration.RetrofitProvider
import com.ahrybuk.swivltest.api.services.UsersService
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun usersApiTest() {

        //"from" and "username" values is hardcoded because this is test proj

        val userService = RetrofitProvider().retrofit.create(UsersService::class.java)

        userService.getUsers().subscribe { users ->
            assertTrue(users.isNotEmpty())
        }

        userService.getUsers(45).subscribe { users ->
            assertTrue(users.isNotEmpty())
        }

        userService.getUserDetail("bmizerany").subscribe { user ->
            assertTrue(user.url != null)
        }
    }
}
