
#import "DeviceLocale.h"

@implementation DeviceLocale

RCT_EXPORT_MODULE();

+ (BOOL)requiresMainQueueSetup
{
   return YES;
}

- (NSString*) deviceLocale
{
    NSString *language = [[NSLocale preferredLanguages] firstObject];
    return language;
}

- (NSArray<NSString *> *) preferredLocales
{
    return [NSLocale preferredLanguages];
}

- (NSString*) deviceCountry 
{
  NSString *country = [[NSLocale currentLocale] objectForKey:NSLocaleCountryCode];
  return country;
}

- (NSString*) timezone
{
  NSTimeZone *currentTimeZone = [NSTimeZone localTimeZone];
  return currentTimeZone.name;
}

- (NSDictionary *)constantsToExport
{
    return @{
             @"deviceLocale": self.deviceLocale ?: [NSNull null],
             @"deviceCountry": self.deviceCountry ?: [NSNull null],
             @"preferredLocales": self.preferredLocales ?: [NSNull null],
             @"timezone": self.timezone ?: [NSNull null],
             @"appVersion": [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"] ?: [NSNull null],
             @"buildNumber": [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleVersion"] ?: [NSNull null],
             @"appName": [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleDisplayName"] ?: [NSNull null],
            };
}
@end