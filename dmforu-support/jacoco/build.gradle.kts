plugins {
    id("jacoco-report-aggregation")
}

dependencies {
    jacocoAggregation(project(":dmforu-api"))
    jacocoAggregation(project(":dmforu-admin"))
}