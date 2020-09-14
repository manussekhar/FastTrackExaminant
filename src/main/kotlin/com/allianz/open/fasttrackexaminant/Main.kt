package com.allianz.open.fasttrackexaminant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@SpringBootApplication
class Main
	@Bean
	fun taskExecutor(): TaskExecutor {
		val executor = ThreadPoolTaskExecutor()
		executor.corePoolSize = 4
		executor.maxPoolSize = 4
		executor.initialize()
		return executor
	}
	fun main(args: Array<String>) {
		runApplication<Main>(*args)
	}

