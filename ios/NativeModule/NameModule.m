//
//  NameModule.m
//  NativeModules
//
//  Created by Yashraj on 31/03/26.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

@interface RCT_EXTERN_MODULE(NameModule, RCTEventEmitter)

RCT_EXTERN_METHOD(openNameScreen)

@end
