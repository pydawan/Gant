//  Gant -- A Groovy way of scripting Ant tasks.
//
//  Copyright © 2009 Russel Winder
//
//  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
//  compliance with the License. You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software distributed under the License is
//  distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
//  implied. See the License for the specific language governing permissions and limitations under the
//  License.

package org.codehaus.gant.tests

/**
 *  A test for the prehook and posthook interceptors.
 *
 *  @author Russel Winder <russel.winder@concertant.com>
 */
final class Hooks_Test extends GantTestCase {
  def targetName = 'trial'
  def flobString = 'flobadob'
  def targetString = 'weed'
  void testDefinePrehookScalar ( ) {
    script = '''
def flob = { println ( "''' + flobString + '''" ) }
target ( name : "''' + targetName + '''" , prehook : flob ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( '' , error )
    assertEquals ( resultString ( targetName , flobString + '\n' + targetString + '\n' ) , output )
  }
  void testDefinePrehookList ( ) {
    script = '''
def flob = { println ( "''' + flobString + '''" ) }
target ( name : "''' + targetName + '''" , prehook : [ flob ] ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( '' , error )
    assertEquals ( resultString ( targetName , flobString + '\n' + targetString + '\n' ) , output )
  }
  void testPrehookScalarWrongTypeError ( ) {
    script = '''
def flob = 1
target ( name : "''' + targetName + '''" , prehook : flob ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( 'Target prehook not a closure or list of closures.\n' , error )
    assertEquals ( resultString ( targetName , targetString + '\n' ) , output )
  }
void testPrehookListWrongTypeError ( ) {
    script = '''
def flob = 1
target ( name : "''' + targetName + '''" , prehook : [ flob ] ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( 'Target prehook list item is not a closure.\n' , error )
    assertEquals ( resultString ( targetName , targetString + '\n' ) , output )
  }
  void testDefinePosthookScalar ( ) {
    script = '''
def flob = { println ( "''' + flobString + '''" ) }
target ( name : "''' + targetName + '''" , posthook : flob ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( '' , error )
    assertEquals ( resultString ( targetName , targetString + '\n' + flobString + '\n' ) , output )
  }
  void testDefinePosthookList ( ) {
    script = '''
def flob = { println ( "''' + flobString + '''" ) }
target ( name : "''' + targetName + '''" , posthook : [ flob ] ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( '' , error )
    assertEquals ( resultString ( targetName , targetString + '\n' + flobString + '\n' ) , output )
  }
  void testPosthookScalarWrongTypeError ( ) {
    script = '''
def flob = 1
target ( name : "''' + targetName + '''" , posthook : flob ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( 'Target posthook not a closure or list of closures.\n' , error )
    assertEquals ( resultString ( targetName , targetString + '\n' ) , output )
  }
void testPosthookListWrongTypeError ( ) {
    script = '''
def flob = 1
target ( name : "''' + targetName + '''" , posthook : [ flob ] ) { println ( "''' + targetString + '''" ) }
'''
    assertEquals ( 0 , processCmdLineTargets ( targetName ) )
    assertEquals ( 'Target posthook list item is not a closure.\n' , error )
    assertEquals ( resultString ( targetName , targetString + '\n' ) , output )
  }
}
