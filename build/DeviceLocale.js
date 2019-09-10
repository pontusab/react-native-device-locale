import { NativeModules } from 'react-native';
const { DeviceLocale } = NativeModules;
export default {
    deviceCountry: DeviceLocale.deviceCountry,
    deviceLocale: DeviceLocale.deviceLocale,
    preferredLocales: DeviceLocale.preferredLocales,
    timezone: DeviceLocale.timezone,
    appVersion: DeviceLocale.appVersion,
    buildNumber: DeviceLocale.buildNumber,
    appName: DeviceLocale.appName
};
//# sourceMappingURL=DeviceLocale.js.map