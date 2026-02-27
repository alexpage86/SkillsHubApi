package com.spotevent;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;

class ArchiUnitTest {

    private static final JavaClasses PROJECT_CLASSES = new ClassFileImporter().importPackages("com.spotevent");
    private static final JavaClasses CLASSES_WITHOUT_TESTS = new ClassFileImporter().withImportOption(
            new com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests()).importPackages("com.spotevent");

    @Test
    void noCycles() {
        var slices = SlicesRuleDefinition.slices().matching("com.spotevent.(*)..");
        var cycles = slices.should().beFreeOfCycles().evaluate(PROJECT_CLASSES).getFailureReport().getDetails();

        assertThat(cycles).isEmpty();
    }

    @Test
    void internal_package_should_not_depend_on_api() {
        ArchRule myRule = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..api..");

        myRule.check(CLASSES_WITHOUT_TESTS);
    }
}
