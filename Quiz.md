#####  What is Lucene?
#####  What is ElasticSearch?
#####  What is Analysis?
    Analysis is the process of converting full text to terms. 
    Depending on which analyzer is used, these phrases: FOO BAR, Foo-Bar, foo,bar will probably all result in the terms 
    foo and bar. These terms are what is actually stored in the index. A full text query (not a term query) for FoO:bAR 
    will also be analyzed to the terms foo,bar and will thus match the terms stored in the index. It is this process of 
    analysis (both at index time and at search time) that allows Elasticsearch to perform full text queries.
#####  What is Cluster?
    A cluster consists of one or more nodes which share the same cluster name.
    Each cluster has a single master node which is chosen automatically by the cluster and which can be replaced if the 
    current master node fails.
#####  What is Document?
    A document is a JSON document which is stored in Elasticsearch. 
    It is like a row in a table in a relational database. 
    Each document is stored in an index and has a type and an id. 
    A document is a JSON object (also known in other languages as a hash / hashmap / associative array) which contains zero or more fields, or key-value pairs. 
    The original JSON document that is indexed will be stored in the _source field, which is returned by default when getting or searching for a document.
#####  What is Document ID?
    The ID of a document identifies a document. 
    The index/id of a document must be unique. If no ID is provided, then it will be auto-generated. 
#####  What is Document Field?
    A document contains a list of fields, or key-value pairs. The value can be a simple (scalar) value 
    (eg a string, integer, date), or a nested structure like an array or an object. 
    A field is similar to a column in a table in a relational database. 
    The mapping for each field has a field type (not to be confused with document type) which indicates the type of data
    that can be stored in that field, eg integer, string, object. The mapping also allows you to define 
    (amongst other things) how the value for a field should be analyzed.
#####  What is Filter?
    A filter is a non-scoring query, meaning that it does not score documents. 
    It is only concerned about answering the question - "Does this document match?". 
    The answer is always a simple, binary yes or no. 
    This kind of query is said to be made in a filter context, hence it is called a filter. 
    Filters are simple checks for set inclusion or exclusion. 
    In most cases, the goal of filtering is to reduce the number of documents that have to be examined.
#####  What is Index?
    An index is like a table in a relational database. 
    It has a mapping which contains a type, which contains the fields in the index. 
    An index is a logical namespace which maps to one or more primary shards and can have zero or more replica shards.
#####  What is Mapping?
    A mapping is like a schema definition in a relational database. Each index has a mapping, which defines a type, 
    plus a number of index-wide settings. A mapping can either be defined explicitly, or it will be generated 
    automatically when a document is indexed.
#####  What is Node?
    A node is a running instance of Elasticsearch which belongs to a cluster. 
    Multiple nodes can be started on a single server for testing purposes, but usually you should have one node per server. 
    At startup, a node will use unicast to discover an existing cluster with the same cluster name and will try to join 
    that cluster.
#####  What is Primary Shard?
    Each document is stored in a single primary shard. When you index a document, it is indexed first on the primary 
    shard, then on all replicas of the primary shard.
    By default, an index has 5 primary shards. 
    You can specify fewer or more primary shards to scale the number of documents that your index can handle. 
    You cannot change the number of primary shards in an index, once the index is created. 
#####  What is Query?
    A query is the basic component of a search. 
    A search can be defined by one or more queries which can be mixed and matched in endless combinations. 
    While filters are queries that only determine if a document matches, those queries that also calculate how well the 
    document matches are known as "scoring queries". 
    Those queries assign it a score, which is later used to sort matched documents. 
    Scoring queries take more resources than non scoring queries and their query results are not cacheable. 
    As a general rule, use query clauses for full-text search or for any condition that requires scoring, and use 
    filters for everything else.
#####  What is Replica Shard?
#####  What is Routing?
#####  What is Shard?
#####  What is Source Field?
#####  What is Term?
#####  What is Text?
#####  What is Type?
    A document is a JSON document which is stored in Elasticsearch. 
    It is like a row in a table in a relational database. 
    Each document is stored in an index and has a type and an id. 
    A document is a JSON object (also known in other languages as a hash / hashmap / associative array) which contains zero or more fields, or key-value pairs. 
    The original JSON document that is indexed will be stored in the _source field, which is returned by default when getting or searching for a document.
#####  What is field?
#####  What is Mapping?
#####  What is Cluster, Node, Shard&Replica?

##### What is Cluster, Node, Shard&Replica?
    A cluster consists of one or more nodes which share the same cluster name. Each cluster has a single master node which is chosen automatically by the cluster and which can be replaced if the current master node fails.
