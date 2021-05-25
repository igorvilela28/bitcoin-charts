clean:
	./gradlew clean

build:
	./gradlew build

unit-tests:
	./gradlew test

ui-tests:
	./gradlew connectAndroidTest

detekt:
	./gradlew detekt
