package co.eliseev.quotes.configuration.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.stereotype.Component

@Aspect
@Component
@EnableAspectJAutoProxy
class LoggingAspect {

    @Around(value = "within(co.eliseev.quotes..*)")
    fun logAround(joinPoint: ProceedingJoinPoint){
        val args = joinPoint.args
        val targetPath = getPath(joinPoint)
        logger.info("$targetPath IN : ${args.toList()}")
        val result = joinPoint.proceed(args)
        logger.info("$targetPath OUT: $result")
    }

    private fun getPath(joinPoint: ProceedingJoinPoint): String =
        "${joinPoint.target.javaClass.canonicalName}.${joinPoint.signature.name}"

    companion object {
        private val logger = LoggerFactory.getLogger(LoggingAspect::class.java)
    }

}
