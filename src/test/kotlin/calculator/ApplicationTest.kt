package calculator

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ApplicationTest : NsTest() {
    @Test
    fun `커스텀 구분자 사용`() {
        assertSimpleTest {
            run("//;\\n1")
            assertThat(output()).contains("결과 : 1")
        }
    }

    @Test
    fun `예외 테스트`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("-1,2,3") }
        }
    }

    @Test
    fun `커스텀 문자열만 입력되었을 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("//123123\n")}
        }
    }

    @Test
    fun `커스텀 문자열을 받고 구분자로 끝났을 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("//123123\n;")}
        }
    }

    @Test
    fun `음수가 입력되었을 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("-2:3:3")}
        }
    }

    @Test
    fun `0이 입력되었을 경우`() {
        assertSimpleTest {
            assertThrows<IllegalArgumentException> { runException("0:3:3")}
        }
    }

    override fun runMain() {
        main()
    }
}
