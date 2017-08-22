package com.fsaucedo.jira;

import com.atlassian.connect.spring.api.auth.AtlassianHostUser;
import com.atlassian.connect.spring.api.host.AtlassianHost;

import com.fasterxml.jackson.databind.JsonNode;

import com.fsaucedo.locallibrary.Company;
import com.fsaucedo.locallibrary.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class WebhookResource {
    private static final Logger log = LoggerFactory.getLogger(WebhookResource.class);

    @RequestMapping(value = "/webhook/issue", method = RequestMethod.POST)
    public ResponseEntity issueCreatedOrUpdated(AtlassianHostUser atlassianHostUser, @RequestBody JsonNode webhookEvent) {
        final AtlassianHost atlassianHost = atlassianHostUser.getHost();
        log.info("Webhook host: {} ({})", atlassianHost.getBaseUrl(), atlassianHost.getClientKey());
        log.info("Webhook user: " + atlassianHostUser.getUserKey());

        final String issueKey = webhookEvent.get("issue").get("key").textValue();
        log.info("Issue key is: " + issueKey);
        log.info("All webhook data: " + webhookEvent);

        Company myCompany = new Company("Test Company", new ArrayList<>());
        myCompany.addEmployee(new Employee("Steve Jobs", "Boss"));
        myCompany.addEmployee(new Employee("Mark Zuckerberg", "Manager"));
        myCompany.addEmployee(new Employee("Jack Dorsey", "Dev"));

        myCompany.printEmployees();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
