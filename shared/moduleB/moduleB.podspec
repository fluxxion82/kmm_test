Pod::Spec.new do |spec|
    spec.name                     = 'moduleB'
    spec.version                  = '0.0.1'
    spec.homepage                 = 'https://github.com/JetBrains/kotlin-native'
    spec.source                   = { :git => "Not Published", :tag => "Cocoapods/#{spec.name}/#{spec.version}" }
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'ModuleB CocoaPods dependencies'

    spec.vendored_frameworks      = "build/cocoapods/framework/moduleB.framework"
    spec.libraries                = "c++"
    spec.module_name              = "#{spec.name}_umbrella"

    spec.ios.deployment_target = '13.5'

    spec.dependency 'SwiftCoroutine', '~> 2.1.11'

    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':moduleB',
        'PRODUCT_MODULE_NAME' => 'moduleB',
    }

    spec.script_phases = [
        {
            :name => 'Build moduleB',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$COCOAPODS_SKIP_KOTLIN_BUILD" ]; then
                  echo "Skipping Gradle build task invocation due to COCOAPODS_SKIP_KOTLIN_BUILD environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration=$CONFIGURATION
            SCRIPT
        }
    ]
end