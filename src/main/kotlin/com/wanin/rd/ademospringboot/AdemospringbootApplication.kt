package com.wanin.rd.ademospringboot

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
@SpringBootApplication
class AdemospringbootApplication{
}


fun main(args: Array<String>) {
	runApplication<AdemospringbootApplication>(*args){
		setBannerMode(Banner.Mode.OFF) //runtime console don't show big icon
	}
}
