# chaseoes' ten.java Submission

**Plugin Name:** MobNinja  
**Theme:** Entities  
**Test Server (1.7.2):** vps.chaseoes.com:1337  
**Download:** [http://ci.chaseoes.com/job/MobNinja/](http://ci.chaseoes.com/job/MobNinja/)  
_Compiled with **Bukkit 1.6.4-R2.0** and compatible with **1.6.x** and **1.7.x** servers._

This is a plugin that was inspired by the Fruit Ninja game for Android. The idea is that you set spawnpoints, mobs will shoot up from them, and you must try and shoot them down while standing on a platform. For each mob you kill you get a point, and the first player to 10 points wins.

## Map Setup
1. Build the physical map. An example map can be found on my test server, **vps.chaseoes.com:1337**. [You can download my example map here](http://chaseoes.com/downloads/mob_ninja_map.zip). A map should contain a platform you shoot the mobs from, and spawnpoints (spots where you want the mobs to shoot up from).
1. Stand on your platform where you want people to spawn when they join the game, and type **/mobninja create <map name>**. This will create the map.
1. Go to each of your spawnpoints, stand on them, and type **/mobninja addspawn <map name>**. This will add a spawn point. When the game starts, mobs will shoot up in the order the spawns were added.
1. If you would like to adjust the maximum amount of players that can join the map, type **/mobninja setmax <map name> <#>**. This defaults to 5 if you do not set it via the command. You can set it to 1 to play by yourself. The game will automatically start when the maximum amount of players is reached.
1. If you want to create a sign to join the game, place a sign with line 1 as `[MobNinja]` and line 2 as the map name.
1. You're done! You can now join and play. Try to shoot the mobs that shoot up with your bow to get a point. The first player to get to 10 points wins the game.

## Commands

<table>
  <tr>
    <th>Command Usage</th><th>Description</th>
  </tr>
  <tr>
    <td>/mobninja join|leave <map name></td>
    <td>Join or leave the specified map.</td>
  </tr>
  <tr>
    <td>/mobninja create <map name></td>
    <td>Create a new map, with the spawn at your current location.</td>
  </tr>
  <tr>
    <td>/mobninja start|stop <map name></td>
    <td>Start or stop the specified map.</td>
  </tr>
  <tr>
    <td>/mobninja addspawn <map name></td>
    <td>Add a spawnpoint where mobs will shoot up from.</td>
  </tr>
  <tr>
    <td>/mobninja setmax <map name> <#></td>
    <td>Set the maximum amount of players that can join (when the game will automatically start).</td>
  </tr>
</table>

## Permissions

<table>
  <tr>
    <th>Permission</th><th>Description</th>
  </tr>
  <tr>
    <td>mobninja.create</td>
    <td>Permission to use `/mobninja create`.</td>
  </tr>
  <tr>
    <td>mobninja.start</td>
    <td>Permission to use `/mobninja start`.</td>
  </tr>
  <tr>
    <td>mobninja.stop</td>
    <td>Permission to use `/mobninja stop`.</td>
  </tr>
  <tr>
    <td>mobninja.addspawn</td>
    <td>Permission to use `/mobninja addspawn`.</td>
  </tr>
  <tr>
    <td>mobninja.setmax</td>
    <td>Permission to use `/mobninja setmax`.</td>
  </tr>
</table>

## Compilation

I use [Maven 3](http://maven.apache.org/download.html) to compile my plugins, using the `package` goal.