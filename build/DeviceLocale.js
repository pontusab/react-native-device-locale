import { NativeModules } from 'react-native';
const { DeviceLocale } = NativeModules;
export default {
    deviceCountry: DeviceLocale.deviceCountry,
    deviceLocale: DeviceLocale.deviceLocale,
    preferredLocales: DeviceLocale.preferredLocales,
    timezone: DeviceLocale.timezone
};
//# sourceMappingURL=DeviceLocale.js.map