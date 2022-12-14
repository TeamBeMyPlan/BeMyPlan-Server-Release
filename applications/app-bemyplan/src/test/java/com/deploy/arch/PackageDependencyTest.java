package com.deploy.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.deploy.bemyplan")
public class PackageDependencyTest {

    @ArchTest
    ArchRule commonNeverDependsOnOthers = noClasses().that().resideInAPackage("..common..")
            .should().dependOnClassesThat().resideInAPackage("..advice..")
            .orShould().dependOnClassesThat().resideInAPackage("..auth..")
            .orShould().dependOnClassesThat().resideInAPackage("..config..")
            .orShould().dependOnClassesThat().resideInAPackage("..order..")
            .orShould().dependOnClassesThat().resideInAPackage("..payment..")
            .orShould().dependOnClassesThat().resideInAPackage("..plan..")
            .orShould().dependOnClassesThat().resideInAPackage("..preview..")
            .orShould().dependOnClassesThat().resideInAPackage("..scrap..")
            .orShould().dependOnClassesThat().resideInAPackage("..temp..")
            .orShould().dependOnClassesThat().resideInAPackage("..user..");

    @ArchTest
    LayeredArchitecture orderLayered = layeredArchitecture()
            .layer("Controller").definedBy("com.deploy.bemyplan.order.controller")
            .layer("Service").definedBy("com.deploy.bemyplan.order.service")
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller");

    @ArchTest
    ArchRule orderCircularDependency = slices().matching("com.deploy.bemyplan.order.(**)")
            .should().beFreeOfCycles();
}