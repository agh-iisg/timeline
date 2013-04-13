Timeline Editor in Eclipse Draw2d
========

This repository contains plugins for two projects: one based on Eclipse 3.x, the seond one on Eclipse 4.x. There're also common plugins that are required for both projects.

The following list presents plugins required for Eclipse 3.x based project:
<ol>
<li>pl.edu.agh.iisg.timeline</li>
<li>pl.edu.agh.iisg.timeline.example</li>
<li>pl.edu.agh.iisg.timeline.feature</li>
<li>pl.edu.agh.iisg.timeline.repository</li>
</ol>

The following list presents plugins required for Eclipse 4.x based project:
<ol>
<li>pl.edu.agh.iisg.timeline.e4</li>
<li>pl.edu.agh.iisg.timeline.e4.example</li>
<li>pl.edu.agh.iisg.timeline.e4.feature</li>
<li>pl.edu.agh.iisg.timeline.e4.repository</li>
</ol>

The following list presents plugins required for both Eclipse 3.x and Eclipse 4.x based projects:
<ol>
<li>pl.edu.agh.iisg.timeline.common</li>
<li>pl.edu.agh.iisg.timeline.common.example</li>
</ol>

To build the projects use Maven (3.0.0 or above) with the following command

    mvn clean verify

Then in pl.edu.agh.iisg.timeline.repository/target/products and pl.edu.agh.iisg.timeline.e4.repository/target/products you will find a ready to use product based on Eclipse 3.x and Eclipse 4.x respectively. Just go to right directory (which is platform dependent) and run eclipse.exe file.

