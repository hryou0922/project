package com.hry.project.dictation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


@SpringBootApplication
@MapperScan("com.hry.project.dictation.mapper")
@EnableTransactionManagement
public class DictationApplication {

	public static void main(String[] args) {
		String profiles = System.getProperty("spring.profiles.active");

		if (StringUtils.isEmpty(profiles) && (args == null || args.length == 0)) {
			args = new String[1];
			args[0] = "--spring.profiles.active=dev";
		}
		SpringApplication.run(DictationApplication.class, args);
	}

	/**
	 * json 的 data 格式统一化格式化
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();

		// 忽略json字符串中不识别的属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 忽略无法转换的对象
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// PrettyPrinter 格式化输出
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		// NULL不参与序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		// 指定时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		// 日期类型字符串处理
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		// java8日期日期处理
		JavaTimeModule memberTimeModule = new JavaTimeModule();
		memberTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		memberTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		memberTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
		memberTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		memberTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		memberTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

		memberTimeModule.addSerializer(Long.class, ToStringSerializer.instance);
		memberTimeModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

		objectMapper.registerModule(memberTimeModule);

		converter.setObjectMapper(objectMapper);
		return converter;
	}
}
