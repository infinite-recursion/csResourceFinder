CREATE TABLE csresource."user"
(
    username character varying(10) COLLATE pg_catalog."default" NOT NULL,
    password character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "User_pkey" PRIMARY KEY (username)
)

TABLESPACE pg_default;

ALTER TABLE csresource."user"
    OWNER to motknxkrkgdslf;


CREATE TABLE csresource.acitivity
(
    date timestamp with time zone NOT NULL,
    type character varying(9) COLLATE pg_catalog."default" NOT NULL,
    typeid character varying(30) COLLATE pg_catalog."default" NOT NULL,
    id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT acitivity_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE csresource.acitivity
    OWNER to motknxkrkgdslf;

CREATE TABLE csresource.resource
(
    name character varying(60) COLLATE pg_catalog."default" NOT NULL,
    url character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    rating numeric(3,2),
    numratings integer,
    description character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Resource_pkey" PRIMARY KEY (name)
)

TABLESPACE pg_default;

ALTER TABLE csresource.resource
    OWNER to motknxkrkgdslf;


CREATE TABLE csresource.resourcequestion
(
    "user" character varying(10) COLLATE pg_catalog."default" NOT NULL,
    date timestamp with time zone NOT NULL,
    likes integer NOT NULL,
    resourcename character varying(60) COLLATE pg_catalog."default" NOT NULL,
    comment character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ResourceQuestion_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_resource_question_resource FOREIGN KEY (resourcename)
        REFERENCES csresource.resource (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_resource_question_user FOREIGN KEY ("user")
        REFERENCES csresource."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE csresource.resourcequestion
    OWNER to motknxkrkgdslf;

CREATE TABLE csresource.resourcereply
(
    "user" character varying(10) COLLATE pg_catalog."default" NOT NULL,
    date timestamp with time zone NOT NULL,
    likes integer NOT NULL,
    resourcename character varying(60) COLLATE pg_catalog."default" NOT NULL,
    comment character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    accepted boolean NOT NULL,
    repliedtoid character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ResourceReply_pkey" PRIMARY KEY (id),
    CONSTRAINT "fk_resource_reply_replyId" FOREIGN KEY (repliedtoid)
        REFERENCES csresource.resourcequestion (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_resource_reply_resource FOREIGN KEY (resourcename)
        REFERENCES csresource.resource (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_resource_reply_user FOREIGN KEY ("user")
        REFERENCES csresource."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE csresource.resourcereply
    OWNER to motknxkrkgdslf;
-- Index: fki_fk_resource_reply_replyId

-- DROP INDEX csresource."fki_fk_resource_reply_replyId";

CREATE INDEX "fki_fk_resource_reply_replyId"
    ON csresource.resourcereply USING btree
    (repliedtoid COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_fk_resource_reply_resource

-- DROP INDEX csresource.fki_fk_resource_reply_resource;

CREATE INDEX fki_fk_resource_reply_resource
    ON csresource.resourcereply USING btree
    (resourcename COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_fk_resource_reply_user

-- DROP INDEX csresource.fki_fk_resource_reply_user;

CREATE INDEX fki_fk_resource_reply_user
    ON csresource.resourcereply USING btree
    ("user" COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE csresource.resourcereview
(
    "user" character varying(10) COLLATE pg_catalog."default" NOT NULL,
    date timestamp with time zone NOT NULL,
    likes integer NOT NULL,
    rating integer NOT NULL,
    resourcename character varying(60) COLLATE pg_catalog."default" NOT NULL,
    comment character varying(1000) COLLATE pg_catalog."default",
    id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ResourceReview_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_resource FOREIGN KEY (resourcename)
        REFERENCES csresource.resource (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY ("user")
        REFERENCES csresource."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE csresource.resourcereview
    OWNER to motknxkrkgdslf;
-- Index: fki_fk_review_resource

-- DROP INDEX csresource.fki_fk_review_resource;

CREATE INDEX fki_fk_review_resource
    ON csresource.resourcereview USING btree
    (resourcename COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_fk_user

-- DROP INDEX csresource.fki_fk_user;

CREATE INDEX fki_fk_user
    ON csresource.resourcereview USING btree
    ("user" COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE csresource.resourcetag
(
    count integer NOT NULL,
    tagname character varying(20) COLLATE pg_catalog."default" NOT NULL,
    resourcename character varying(60) COLLATE pg_catalog."default" NOT NULL,
    id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ResourceTag_pkey" PRIMARY KEY (id),
    CONSTRAINT fk_tag_name FOREIGN KEY (tagname)
        REFERENCES csresource.tag (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_tag_resource FOREIGN KEY (resourcename)
        REFERENCES csresource.resource (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

CREATE TABLE csresource.tag
(
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Tag_pkey" PRIMARY KEY (name)
)

TABLESPACE pg_default;

ALTER TABLE csresource.tag
    OWNER to motknxkrkgdslf;

ALTER TABLE csresource.resourcetag
    OWNER to motknxkrkgdslf;
-- Index: fki_fk_resource_name

-- DROP INDEX csresource.fki_fk_resource_name;

CREATE INDEX fki_fk_resource_name
    ON csresource.resourcetag USING btree
    (resourcename COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_fk_tag_name

-- DROP INDEX csresource.fki_fk_tag_name;

CREATE INDEX fki_fk_tag_name
    ON csresource.resourcetag USING btree
    (tagname COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE csresource.userlikes
(
    username character varying(10) COLLATE pg_catalog."default" NOT NULL,
    contentid character varying(30) COLLATE pg_catalog."default" NOT NULL,
    contenttype character varying(8) COLLATE pg_catalog."default" NOT NULL,
    id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT userlikes_pkey PRIMARY KEY (id),
    CONSTRAINT fk_like_user FOREIGN KEY (username)
        REFERENCES csresource."user" (username) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE csresource.userlikes
    OWNER to motknxkrkgdslf;
-- Index: fki_fk_like_user

-- DROP INDEX csresource.fki_fk_like_user;

CREATE INDEX fki_fk_like_user
    ON csresource.userlikes USING btree
    (username COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;