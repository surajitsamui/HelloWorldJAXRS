/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */
package helloworld;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author mkuchtiak
 */
@Stateless
@Path("/greeting")
public class HelloWorldResource {

    @EJB
    private NameStorageBean nameStorage;

    /**
     * Retrieves representation of an instance of helloworld.HelloWorldResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getGreeting() {
        return "<html><body><h1>Hello " + nameStorage.getName() + "!</h1></body></html>";
    }

    /**
     * PUT method for updating an instance of HelloWorldResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void setName(String content) {
        nameStorage.setName(content);
    }

    //path param
    @GET
    @Path("{year}/{month}/{day}")
    public Response getDateYearMonth(@PathParam("year") int year, @PathParam("month") int month, @PathParam("day") int day) {
        String date = year + "/" + month + "/" + day;
        return Response.status(200).entity("getUserHistory is called, year/month/day : " + date).build();

    }

    //query param
    @GET
    @Path("/query")
    public Response getUsers(@QueryParam("from") int from, @QueryParam("to") int to, @QueryParam("orderBy") List<String> orderBy) {
        return Response.status(200).entity("getUsers is called, from : " + from + ", to : " + to + ", orderBy" + orderBy.toString()).build();
    }
}
