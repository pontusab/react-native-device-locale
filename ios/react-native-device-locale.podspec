require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = 'DeviceLocale'
  s.version      = package['version']
  s.summary      = package['description']
  s.license      = package['license']

  s.authors      = package['author']
  s.homepage     = package['homepage']
  s.platform     = :ios, "9.0"

  s.source       = { :git => "https://github.com/pontusab/react-native-device-locale.git", :tag => "v#{s.version}" }
  s.source_files   = 'DeviceLocale/**/*.{h,m}'
  s.preserve_paths = 'DeviceLocale/**/*.{h,m}'
 
  s.dependency 'React'
end