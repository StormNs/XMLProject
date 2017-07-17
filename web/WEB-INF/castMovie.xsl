<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : newstylesheet.xsl
    Created on : July 17, 2017, 10:16 PM
    Author     : StormNs
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <table class="casting-table">
            <tbody>
                <xsl:for-each select="actors/actor">
                    <tr class="caster">
                        <td>
                            <xsl:if test="contains(img,'http')">
                                <img class="cast-img" src="{img}" />
                            </xsl:if>
                            <xsl:if test="not(contains(img,'http'))">
                                <img class="cast-img" src="FileServlet/{img}" />
                            </xsl:if>
                        </td>
                        <td class="cast-name">
                            <xsl:value-of select="name"/>
                        </td>
                        <td class="cast-char">
                            <xsl:value-of select="character"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </xsl:template>

</xsl:stylesheet>
