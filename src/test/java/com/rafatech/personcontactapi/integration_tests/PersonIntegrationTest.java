package com.rafatech.personcontactapi.integration_tests;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.rafatech.personcontactapi.integration_tests.Scripts.SCRIPT_PERSON_DELETE;
import static com.rafatech.personcontactapi.integration_tests.Scripts.SCRIPT_PERSON_INSERT;

@SqlGroup({
        @Sql(scripts = {
                SCRIPT_PERSON_INSERT
        }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {
                SCRIPT_PERSON_DELETE
        }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public abstract class PersonIntegrationTest extends AbstractIntegrationTest {
}
