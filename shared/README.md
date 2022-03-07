run `./build.sh` to build and install pods.

Three modules in shared project:

- moduleA - interface and global object (Foo, FooFighterIos)
- moduleB - impl of moduleA interface
- moduleC - uses moduleA interface (Bar)

ios has two buttons: 
- first button is to do register Foos with FooFighter object (add to foo list)
- second button call moduleC.Bar to make calls to Foo

FooFighter name tag persists across calls. Foo list does not.
