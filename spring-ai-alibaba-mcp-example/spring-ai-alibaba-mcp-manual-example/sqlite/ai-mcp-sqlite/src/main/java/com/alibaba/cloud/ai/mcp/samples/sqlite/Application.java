package com.alibaba.cloud.ai.mcp.samples.sqlite;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner predefinedQuestions(ChatClient.Builder chatClientBuilder,
			List<McpSyncClient> mcpClients, ConfigurableApplicationContext context) {

		return args -> {
			var chatClient = chatClientBuilder
					.defaultToolCallbacks(new SyncMcpToolCallbackProvider(mcpClients))
					.build();
			System.out.println("Running predefined questions with AI model responses:\n");

			// Question 1
			String question1 = "Can you connect to my SQLite database and tell me what products are available, and their prices?";
			System.out.println("QUESTION: " + question1);
			System.out.println("ASSISTANT: " + chatClient.prompt(question1).call().content());

			// Question 2
			String question2 = "What's the average price of all products in the database?";
			System.out.println("\nQUESTION: " + question2);
			System.out.println("ASSISTANT: " + chatClient.prompt(question2).call().content());

			// Question 3
			String question3 = "Can you analyze the price distribution and suggest any pricing optimizations?";
			System.out.println("\nQUESTION: " + question3);
			System.out.println("ASSISTANT: " + chatClient.prompt(question3).call().content());

			// Question 4
			String question4 = "Could you help me design and create a new table for storing customer orders?";
			System.out.println("\nQUESTION: " + question4);
			System.out.println("ASSISTANT: " + chatClient.prompt(question4).call().content());

			System.out.println("\nPredefined questions completed. Exiting application.");
			context.close();

		};
	}

	@Bean(destroyMethod = "close")
	public McpSyncClient mcpClient() {

		// Windows 系统需要修改 uvx.exe
		var stdioParams = ServerParameters.builder("D:\\apps\\anaconda3\\pkgs\\uv-0.7.15-he94b42d_0\\Library\\bin\\uvx.exe")
				.args("mcp-server-sqlite", "--db-path",
						getDbPath())
				.build();

		var mcpClient = McpClient.sync(new StdioClientTransport(stdioParams))
				.requestTimeout(Duration.ofSeconds(10)).build();

		var init = mcpClient.initialize();

		System.out.println("MCP Initialized: " + init);

		return mcpClient;

	}

	private static String getDbPath() {

		String path = Paths.get(System.getProperty("user.dir"), "\\spring-ai-alibaba-mcp-example\\spring-ai-alibaba-mcp-manual-example\\sqlite\\ai-mcp-sqlite\\", "test.db").toString();
		System.out.println(path);

		return path;
	}

}