package com.github.fabriciolfj.javaexamples;

import com.github.fabriciolfj.javaexamples.threads.DemonstrationRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.weaving.AspectJWeavingEnabler;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableMBeanExport
@SpringBootApplication
@EnableJpaRepositories("com.github.fabriciolfj.javaexamples.repository")
@EnableAspectJAutoProxy
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@ComponentScan(basePackages = "com.github.fabriciolfj.javaexamples")
//@EnableLoadTimeWeaving
//@EnableSpringConfigured
public class JavaExamplesApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Autowired
	private DemonstrationRunnable runnable;

	public static void main(String[] args) {
		SpringApplication.run(JavaExamplesApplication.class, args);
		/*try (var ctx = new AnnotationConfigApplicationContext()) {
			ctx.registerBean(PrefixGenerator.class, () ->
					new DatePrefixGenerator("yyyyMMdd"));
			ctx.registerBean(Sequence.class, () -> {
				var seq = new Sequence("A", 100000);
				ctx.getBeanProvider(PrefixGenerator.class)
						.ifUnique(seq::setPrefixGenerator);
				return seq;
			}, new SequenceBeanDefinitionCustomizer());
			ctx.refresh();
			var generator = ctx.getBean(Sequence.class);
			System.out.println(generator.getSequence());
			System.out.println(generator.getSequence());
		}*/
	}

	@Override
	public void run(String... args) throws Exception {
		//threadPoolTaskExecutor.execute(runnable);

		for (int i = 0; i <= 500; i++) {
			threadPoolTaskExecutor.submit(runnable);
		}
	}

	/*@Bean
	public static AspectJWeavingEnabler aspectJWeavingEnabler() {
		return new AspectJWeavingEnabler();
	}*/

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JavaExamplesApplication.class);
	}
}
