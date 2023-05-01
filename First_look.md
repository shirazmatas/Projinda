Programmet Calender.java kan framgångsrikt ta en iCalender-länk och skapa ett läsbart ics dokument (iCalender).

  

## Exempel på event: 
|   |
| - |
|BEGIN:VEVENT<br>SUMMARY:Datorlaboration - Sannolikhetsteori och statistik med tillämpning inom maskininlärning (SF1935)<br>DTSTART;VALUE=DATE-TIME:20230516T080000Z<br>DTEND;VALUE=DATE-TIME:20230516T100000Z<br>DTSTAMP;VALUE=DATE-TIME:20221103T023439Z<br>UID:ec995cc67bedd10621af28ad43a124cb0ef533bd<br>DESCRIPTION:https://www.kth.se/social/course/SF1935/subgroup/vt-2023-386/event/652584-2/\n\nTillhör: VT 2023\n\n<br>LAST-MODIFIED;VALUE=DATE-TIME:20221103T023439Z<br>LOCATION:Baltzar\, Christopher<br>STATUS:CONFIRMED<br>END:VEVENT|
|   |
<!-- Kopierbar version:
BEGIN:VEVENT
SUMMARY:Datorlaboration - Sannolikhetsteori och statistik med tillämpning
  inom maskininlärning (SF1935)
DTSTART;VALUE=DATE-TIME:20230516T080000Z
DTEND;VALUE=DATE-TIME:20230516T100000Z
DTSTAMP;VALUE=DATE-TIME:20221103T023439Z
UID:ec995cc67bedd10621af28ad43a124cb0ef533bd
DESCRIPTION:https://www.kth.se/social/course/SF1935/subgroup/vt-2023-386/e
 vent/652584-2/\n\nTillhör: VT 2023\n\n
LAST-MODIFIED;VALUE=DATE-TIME:20221103T023439Z
LOCATION:Baltzar\, Christopher
STATUS:CONFIRMED
END:VEVENT -->
Om man ignorerar några rader förstår man lätt vad som de menar.

### Probem 1
Just detta event är inte mellan 08:00-10:00 eftersom DT i t.ex DTSTART betyder att tiden är för UTC tid som är en timme efter svensk normaltid (CET) eller två timmar efter svensk sommartid (CEST). 
//TODO
Vi behöver veta när bytet från CET till CEST är för att korrekt visa händelser i vår kalender.
